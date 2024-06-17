package org.omrbranch.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootPojo {
	private int status;
    private String message;
    private Datum data;
    private String refer_msg;
    private int cart_count;
    private String role;

}
