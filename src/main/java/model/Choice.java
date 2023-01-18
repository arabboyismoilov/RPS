package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    This class represents choices like rock or paper
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choice {
    private int id;
    private String name;
}
