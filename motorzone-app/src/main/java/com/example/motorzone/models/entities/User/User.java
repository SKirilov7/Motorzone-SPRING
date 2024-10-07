package com.example.motorzone.models.entities.User;

import com.example.motorzone.models.entities.car.CarOffer;
import com.example.motorzone.models.entities.motorcycle.MotorcycleOffer;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    private UserAvatarImage avatar;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean isActive = true;

    @OneToMany(
            mappedBy = "seller",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CarOffer> myCarOffers = new ArrayList<>();

    @OneToMany(
            mappedBy = "seller",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<MotorcycleOffer> myMotorcycleOffers = new ArrayList<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "user_favorite_car_offers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "car_offer_id")
    )
    private List<MotorcycleOffer> favoriteCarOffers = new ArrayList<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "user_favorite_motorcycle_offers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "motorcycle_offer_id")
    )
    private List<MotorcycleOffer> favoriteMotorcycleOffers = new ArrayList<>();

    public User() {}

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public UserAvatarImage getAvatar() {
        return avatar;
    }

    public User setAvatar(UserAvatarImage avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public User setActive(boolean active) {
        isActive = active;
        return this;
    }

    public List<CarOffer> getMyCarOffers() {
        return myCarOffers;
    }

    public User setMyCarOffers(List<CarOffer> myCarOffers) {
        this.myCarOffers = myCarOffers;
        return this;
    }

    public List<MotorcycleOffer> getMyMotorcycleOffers() {
        return myMotorcycleOffers;
    }

    public User setMyMotorcycleOffers(List<MotorcycleOffer> myMotorcycleOffers) {
        this.myMotorcycleOffers = myMotorcycleOffers;
        return this;
    }

    public List<MotorcycleOffer> getFavoriteCarOffers() {
        return favoriteCarOffers;
    }

    public User setFavoriteCarOffers(List<MotorcycleOffer> favoriteCarOffers) {
        this.favoriteCarOffers = favoriteCarOffers;
        return this;
    }

    public List<MotorcycleOffer> getFavoriteMotorcycleOffers() {
        return favoriteMotorcycleOffers;
    }

    public User setFavoriteMotorcycleOffers(List<MotorcycleOffer> favoriteMotorcycleOffers) {
        this.favoriteMotorcycleOffers = favoriteMotorcycleOffers;
        return this;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}
