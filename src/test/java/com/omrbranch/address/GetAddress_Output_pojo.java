package com.omrbranch.address;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAddress_Output_pojo {
	 private int status;
	    private String message;
	    private ArrayList<Datum> data;

}
