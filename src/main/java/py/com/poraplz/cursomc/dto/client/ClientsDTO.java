package py.com.poraplz.cursomc.dto.client;

import py.com.poraplz.cursomc.module.client.infraestructure.db.jpa.ClientEntity;
import py.com.poraplz.cursomc.module.direction.infraestructure.db.jpa.DirectionEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientsDTO {
    private String name;
    private String email;
    private String cpfOuCnpj;
    private Integer type;
    private List<DirectionEntity> adresses = new ArrayList<>();
    private Set<String> phone = new HashSet<>();

    public ClientsDTO() {
    }

    public ClientsDTO(ClientEntity clientEntity){
        this.name = clientEntity.getName();
        this.email = clientEntity.getEmail();
        this.cpfOuCnpj = clientEntity.getCpfOuCnpj();
        this.type = clientEntity.getType().getCod();
        this.adresses = clientEntity.getAddresses();
        this.phone = clientEntity.getPhone();

    }

    public ClientsDTO(String name, String email, String cpfOuCnpj, Integer type, List<DirectionEntity> adresses, Set<String> phone) {
        this.name = name;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.type = type;
        this.adresses = adresses;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<DirectionEntity> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<DirectionEntity> adresses) {
        this.adresses = adresses;
    }

    public Set<String> getPhone() {
        return phone;
    }

    public void setPhone(Set<String> phone) {
        this.phone = phone;
    }


}
