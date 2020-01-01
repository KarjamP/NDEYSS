#include <stdio.h>
/*
Experiment to check if particular algorithms relating to
floating-point maths would yield a gradually-changing shape.

One failed algorithm, one which is a success.
*/

int main()
{
    double GraduallyChangingShape, TheSight;

    GraduallyChangingShape = -105;
    TheSight=10000;

    while (1)
    {
        /* Wrong; Shape Remains Frozen */
        /*
        GraduallyChangingShape = TheSight / 2;
        */

        
         /*Correct: The simulated shape changes gradually by fading into the correct
                    number.
        */
        GraduallyChangingShape = (GraduallyChangingShape + TheSight) / 2;

        printf("%f \n", GraduallyChangingShape);

        /* Making sure we're able to see the variable at idividual points in time. */
        getchar();
    }
    return 0;
}
