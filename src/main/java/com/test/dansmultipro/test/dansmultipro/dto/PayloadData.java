package com.test.dansmultipro.test.dansmultipro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayloadData {
    String id;
    String type;
    String url;
    String created_at;
    String company;
    String company_url;
    String location;
    String title;
    String description;
    String how_toApply;
    String company_logo;
}
