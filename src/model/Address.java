package model;

public class Address {
    private final String street;
    private final String city;
    private final String zipCode;

    // Private constructor to enforce the use of the AddressBuilder
    private Address(AddressBuilder builder) {
        this.street = builder.street;
        this.city = builder.city;
        this.zipCode = builder.zipCode;
    }

    // Getter methods for accessing Address fields
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    // Static nested AddressBuilder class
    public static class AddressBuilder {
        private String street;
        private String city;
        private String zipCode;

        public AddressBuilder setStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    @Override
    public String toString() {
        return street + ", " + city + " - " + zipCode;
    }
}
