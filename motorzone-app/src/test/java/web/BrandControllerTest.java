package web;

import com.example.motorzone.MotorzoneApplication;
import com.example.motorzone.config.JwtRequestFilter;
import com.example.motorzone.models.dto.brand.CreateBrandDTO;
import com.example.motorzone.models.dto.brand.UpdateBrandDTO;
import com.example.motorzone.models.entities.Brand;
import com.example.motorzone.models.entities.Model;
import com.example.motorzone.models.entities.User.Role;
import com.example.motorzone.models.entities.User.User;
import com.example.motorzone.models.enums.MainCategoryEnum;
import com.example.motorzone.models.enums.UserRoleEnum;
import com.example.motorzone.repositories.BrandRepository;
import com.example.motorzone.repositories.ModelRepository;
import com.example.motorzone.repositories.RoleRepository;
import com.example.motorzone.repositories.UserRepository;
import com.example.motorzone.services.impl.CustomUserDetailsService;
import com.example.motorzone.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.TestPropertySource;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MotorzoneApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private long TEST_BRAND1_ID, TEST_BRAND2_ID;
    private String TEST_BRAND1_NAME = "BMW", TEST_BRAND2_NAME = "Audi";

    private final String TEST_BRAND1_MODEL_NAME = "520";

    private String testToken;

    @BeforeEach
    public void setUp() {
        Brand brand1 = new Brand();
        brand1.setName(TEST_BRAND1_NAME);

        Model model1 = new Model();
        model1.setName(TEST_BRAND1_MODEL_NAME);
        model1.setCategory(MainCategoryEnum.CAR);
        model1.setBrand(brand1);

        brand1.setModels(List.of(model1));
        brandRepository.save(brand1);

        TEST_BRAND1_ID = brand1.getId();

        Brand brand2 = new Brand();
        brand2.setName(TEST_BRAND2_NAME);
        brandRepository.save(brand2);
        TEST_BRAND2_ID = brand2.getId();
    }

    @AfterEach
    public void tearDown() {
        brandRepository.deleteAll();
        modelRepository.deleteAll();
    }

    @Test
    public void testCreateNewBrandWithNotAuthorizedUser () throws Exception {
        CreateBrandDTO newBrand = new CreateBrandDTO();
        newBrand.setName("Mercedes");

        String brandJson = objectMapper.writeValueAsString(newBrand);

        mockMvc.perform(post("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandJson))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void testCreateNewBrandWithUserRole () throws Exception {
        CreateBrandDTO newBrand = new CreateBrandDTO();
        newBrand.setName("Mercedes");

        String brandJson = objectMapper.writeValueAsString(newBrand);

        mockMvc.perform(post("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandJson))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAttemptToCreateANewBrandWithoutName() throws Exception {
        CreateBrandDTO newBrandWithoutName = new CreateBrandDTO();

        String brandJson = objectMapper.writeValueAsString(newBrandWithoutName);

        mockMvc.perform(post("/api/brands")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(brandJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Brand name is required"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAttemptToCreateANewBrandWithBrandWithSmallerLength() throws Exception {
        CreateBrandDTO newBrandWithInvalidName = new CreateBrandDTO();
        newBrandWithInvalidName.setName("a");

        String brandJson = objectMapper.writeValueAsString(newBrandWithInvalidName);

        mockMvc.perform(post("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Brand name must be between 2 and 100 characters"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAttemptToCreateANewBrandWithBrandWithBiggerLength() throws Exception {
        CreateBrandDTO newBrandWithInvalidName = new CreateBrandDTO();
        newBrandWithInvalidName.setName("a".repeat(101));

        String brandJson = objectMapper.writeValueAsString(newBrandWithInvalidName);

        mockMvc.perform(post("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Brand name must be between 2 and 100 characters"));
    }

    @Test
    public void testBrandNotFound() throws Exception {
        mockMvc.perform(get("/api/brands/100000000"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAttemptToUpdateANonExistingBrand() throws Exception {
        UpdateBrandDTO updateBrandDto = new UpdateBrandDTO();
        updateBrandDto.setName("Kia");

        String brandJson = objectMapper.writeValueAsString(updateBrandDto);

        mockMvc.perform(patch("/api/brands/100000000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandJson))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAttemptToUpdateAInvalidLength() throws Exception {
        UpdateBrandDTO updateBrandDto = new UpdateBrandDTO();
        updateBrandDto.setName("a");

        String brandJson = objectMapper.writeValueAsString(updateBrandDto);

        mockMvc.perform(patch("/api/brands/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Brand name must be between 2 and 100 characters"));
    }

    @Test
    public void testBrandsReturnCorrectStatusCode() throws Exception {
        mockMvc.perform(get("/api/brands/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testBrandsReturnsCorrectCount() throws Exception {
        mockMvc.perform(get("/api/brands/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testBrandsReturnCorrectData() throws Exception {
        mockMvc.perform(get("/api/brands/all?expand=models"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("BMW"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].models.length()").value(1))
                .andExpect(jsonPath("$[0].models[0].name").value("520"))
                .andExpect(jsonPath("$[1].name").value("Audi"))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testBrandsIdReturnCorrectBrandData() throws Exception {
        mockMvc.perform(get("/api/brands/1"))
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.name").value("BMW"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.models.length()").value(1))
                .andExpect(jsonPath("$.models[0].name").value("520"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void createNewBrandAndValidateValues() throws Exception {
        CreateBrandDTO newBrand = new CreateBrandDTO();
        newBrand.setName("Mercedes");

        String brandJson = objectMapper.writeValueAsString(newBrand);

        mockMvc.perform(post("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mercedes"));

        mockMvc.perform(get("/api/brands/all?expand=models"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[2].name").value("Mercedes"))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].models.length()").value(0));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testUpdateABrandAndValidateValues() throws Exception {
        UpdateBrandDTO updateBrandDto = new UpdateBrandDTO();
        updateBrandDto.setName("Volvo");

        String brandJson = objectMapper.writeValueAsString(updateBrandDto);

        mockMvc.perform(patch("/api/brands/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Volvo"));
    }

}
