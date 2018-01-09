package cc.jorgen.server.api;

import com.atexpose.Expose;

/**
 * Purpose of this file is ...
 * <p>
 * Created by Jorgen Andersson on 2018-01-08.
 */
public class Multiply {

    @Expose(
            arguments = {"Product", "MultiplyBy"},
            requiredArgumentCount = 2,
            labels = {"Jorgen"},
            description = "Multiply two ints"
    )
    public static int Multiply(int product, int multiplyBy) {
        return product * multiplyBy;
    }
}
