package com.dental.project.payload.Company;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.dental.project.util.AppConstants;

public class CompanyTypeUpdateRequest extends  CompanyTypeCreateRequest{

    @NotNull
    @Min(AppConstants.MIN_NUMBER_FOR_ID_FIELD)
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
