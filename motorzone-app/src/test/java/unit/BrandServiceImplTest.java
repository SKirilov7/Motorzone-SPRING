package unit;

import com.example.motorzone.exceptions.BrandAlreadyExistsException;
import com.example.motorzone.exceptions.BrandNotExistingException;
import com.example.motorzone.models.dto.brand.BrandDTO;
import com.example.motorzone.models.dto.brand.BrandWithModelsDTO;
import com.example.motorzone.models.dto.brand.CreateBrandDTO;
import com.example.motorzone.models.dto.brand.UpdateBrandDTO;
import com.example.motorzone.models.entities.Brand;
import com.example.motorzone.models.entities.Model;
import com.example.motorzone.repositories.BrandRepository;
import com.example.motorzone.services.impl.BrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;


    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BrandServiceImpl brandService;

    private Brand brand;

    private BrandDTO brandDTO;

    private BrandWithModelsDTO brandWithModelsDTO;

    @BeforeEach
    void setUp() {
        brand = new Brand();
        brand.setId(1L);
        brand.setName("BMW");

        brandDTO = new BrandDTO();
        brandDTO.setId(1L);
        brandDTO.setName("BMW");

        brandWithModelsDTO = new BrandWithModelsDTO();
        brandWithModelsDTO.setId(1L);
        brandWithModelsDTO.setName("BMW");
    }

    @Test
    void getAllBrandsShouldReturnBrandDTOs() {
        when(brandRepository.findAllBrandDTOs()).thenReturn(List.of(brandDTO));

        List<BrandDTO> result = brandService.getAllBrands();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("BMW", result.get(0).getName());
        verify(brandRepository).findAllBrandDTOs();
    }

    @Test
    void getAllBrandsWithModelsShouldReturnBrandWithModelsDTOs() {
        Model model = new Model();
        model.setId(1L);
        model.setName("520");

        brand.setModels(List.of(model));

        when(brandRepository.findAll()).thenReturn(List.of(brand));

        List<BrandWithModelsDTO> result = brandService.getAllBrandsWithModels();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("BMW", result.get(0).getName());
        assertEquals(1, result.get(0).getModels().size());
        assertEquals("520", result.get(0).getModels().get(0).getName());
        verify(brandRepository).findAll();
    }

    @Test
    void getByIdShouldReturnBrandWithModelsDTOWhenBrandExists() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        when(modelMapper.map(any(Brand.class), eq(BrandWithModelsDTO.class)))
                .thenReturn(brandWithModelsDTO);

        BrandWithModelsDTO result = brandService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("BMW", result.getName());
        verify(brandRepository).findById(1L);
        verify(modelMapper).map(any(Brand.class), eq(BrandWithModelsDTO.class));
    }

    @Test
    void getByIdShouldThrowBrandNotExistingExceptionWhenBrandDoesNotExist() {
        when(brandRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(BrandNotExistingException.class, () -> brandService.getById(2L));
        verify(brandRepository).findById(2L);
    }

    @Test
    void createShouldCreateBrandWhenBrandDoesNotExist() {
        CreateBrandDTO createBrandDTO = new CreateBrandDTO();
        createBrandDTO.setName("Audi");

        BrandWithModelsDTO newBrandWithModelsDTO = new BrandWithModelsDTO();
        newBrandWithModelsDTO.setId(2L);
        newBrandWithModelsDTO.setName("Audi");

        when(brandRepository.findByName("Audi")).thenReturn(Optional.empty());
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        when(modelMapper.map(any(CreateBrandDTO.class), eq(Brand.class)))
                .thenReturn(brand);
        when(modelMapper.map(any(Brand.class), eq(BrandWithModelsDTO.class)))
                .thenReturn(newBrandWithModelsDTO);

        BrandWithModelsDTO result = brandService.create(createBrandDTO);

        assertNotNull(result);
        assertEquals("Audi", result.getName());
        verify(brandRepository).findByName("Audi");
        verify(brandRepository).save(any(Brand.class));
        verify(modelMapper).map(any(CreateBrandDTO.class), eq(Brand.class));
        verify(modelMapper).map(any(Brand.class), eq(BrandWithModelsDTO.class));
    }

    @Test
    void createShouldThrowBrandAlreadyExistsExceptionWhenBrandAlreadyExists() {
        CreateBrandDTO createBrandDTO = new CreateBrandDTO();
        createBrandDTO.setName("BMW");

        when(brandRepository.findByName("BMW")).thenReturn(Optional.of(brand));

        assertThrows(BrandAlreadyExistsException.class, () -> brandService.create(createBrandDTO));
        verify(brandRepository).findByName("BMW");
    }

    @Test
    void updateShouldUpdateBrandWhenBrandExists() {
        UpdateBrandDTO updateBrandDTO = new UpdateBrandDTO();
        updateBrandDTO.setName("Mercedes");

        BrandWithModelsDTO updatedBrandWithModelsDTO = new BrandWithModelsDTO();
        updatedBrandWithModelsDTO.setId(1L);
        updatedBrandWithModelsDTO.setName("Mercedes");

        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        when(brandRepository.findByName("Mercedes")).thenReturn(Optional.empty());
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        when(modelMapper.map(any(Brand.class), eq(BrandWithModelsDTO.class)))
                .thenReturn(updatedBrandWithModelsDTO);

        BrandWithModelsDTO result = brandService.update(1L, updateBrandDTO);

        assertNotNull(result);
        assertEquals("Mercedes", result.getName());
        verify(brandRepository).findById(1L);
        verify(brandRepository).findByName("Mercedes");
        verify(brandRepository).save(any(Brand.class));
        verify(modelMapper).map(any(Brand.class), eq(BrandWithModelsDTO.class));
    }

    @Test
    void updateShouldThrowBrandAlreadyExistsExceptionWhenBrandWithSameNameExists() {
        UpdateBrandDTO updateBrandDTO = new UpdateBrandDTO();
        updateBrandDTO.setName("Honda");

        Brand anotherBrand = new Brand();
        anotherBrand.setId(2L);
        anotherBrand.setName("Honda");

        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        when(brandRepository.findByName("Honda")).thenReturn(Optional.of(anotherBrand));

        assertThrows(BrandAlreadyExistsException.class, () -> brandService.update(1L, updateBrandDTO));
        verify(brandRepository).findById(1L);
        verify(brandRepository).findByName("Honda");
    }

    @Test
    void updateShouldThrowBrandNotExistingExceptionWhenBrandDoesNotExist() {
        UpdateBrandDTO updateBrandDTO = new UpdateBrandDTO();
        updateBrandDTO.setName("Mercedes");

        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BrandNotExistingException.class, () -> brandService.update(1L, updateBrandDTO));
        verify(brandRepository).findById(1L);
    }

}
