package com.example.agile_db_api.requestDTO;


import com.example.agile_db_api.enums.Subscription;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter
@Getter
public class OrgRequestDTO {

    @NonNull
     String orgName;
    @NonNull
     String email;
    @NonNull
     String password;
    @NonNull
     Subscription subscription;

}
