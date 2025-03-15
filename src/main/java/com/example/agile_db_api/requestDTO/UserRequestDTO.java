package com.example.agile_db_api.requestDTO;

import com.example.agile_db_api.enums.Role;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserRequestDTO {

        @NonNull
        String name;
        @NonNull
        String Email;
        @NonNull
        String password;
        @NonNull
        Role role;
        @NonNull
        String OrgId;

}

