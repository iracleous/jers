package gr.quedgq.jers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee implements Serializable {

    private int id;
    private String name;



}