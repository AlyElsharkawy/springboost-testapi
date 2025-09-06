package com.example.springboot.dto.bol;

public class BillOfLadingDTORequest {
    String nbr;
    Long companyId;

    // These getters were added so that my LSP could shut up
    // I use neovim as my IDE and I am too lazy to configure the LSP manually
    // So yeah...I added these getters and constructors instead

    public BillOfLadingDTORequest(String nbr, Long companyId) {
        this.nbr = nbr;
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public String getNbr() {
        return this.nbr;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public void setNbr(String nbr) {
        this.nbr = nbr;
    }
}
