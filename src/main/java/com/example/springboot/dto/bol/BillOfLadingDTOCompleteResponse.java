package com.example.springboot.dto.bol;

import java.util.List;
import com.example.springboot.dto.bol.BillOfLadingDetailDTOResponse;

public class BillOfLadingDTOCompleteResponse {
    private String nbr;
    private String companyName;
    private List<BillOfLadingDetailDTOResponse> details;

    public BillOfLadingDTOCompleteResponse() {
    }

    public BillOfLadingDTOCompleteResponse(String nbr, String companyName,
            List<BillOfLadingDetailDTOResponse> in) {
        this.nbr = nbr;
        this.companyName = companyName;
        this.details = in;
    }

    public String getNbr() {
        return this.nbr;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public List<BillOfLadingDetailDTOResponse> getDetails() {
        return this.details;
    }

    public void setNbr(String nbr) {
        this.nbr = nbr;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setDetails(List<BillOfLadingDetailDTOResponse> details) {
        this.details = details;
    }
}
