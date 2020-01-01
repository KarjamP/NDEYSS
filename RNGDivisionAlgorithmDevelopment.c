#include <stdio.h>

/* An attempt to develop a custom-made random number generator for double-precision
   numbers without the usage of XOR, integers and modulo.

   The point is to be able to have a random number generator outside the enviroment
   of integers, allowing for use within contexts with weak integer support,
   but strong floating-point support. The point is also to program a random
   number generator entirely separate from that provided by a programming library,
   to prevent interfering with the functions of other parts of the program.

   This is vital to the NDEYSS project to prevent the AI from being able to manipulate
   far more than they should've within the simulations...

   (Now I know what programming pains feel like...)

   Note that I'm intentionally keeping this a bit untidy, to show that yes,
   even I fall victim to things many programmers experience, as much as I'd hate
   how much time wasted figuring things out like this does...
*/

/*Was used for Modulo simulation; the modulo operation in C
  does not support floating-point precisions.

  Now, it's being repurposed for the sake of figuring out
  how to create a random number out of a double...
*/

#define DIVISIONNUMBER 200.0

double makeshiftAbs (double number) /*easier on the documentation and my typing.*/
{
    if (number <= 0)
        return number*-1;
    else
     return number;    
}



int main ()
    {
        double randomno = 10.0;
        /*Debugging of failed code
        double AValue;
        double Bvalue;
        double cValue;*/
        while (1)
        {
           /*Old work of failed exercise; not qualified enough to invent new maths.
           /* randomno = (cValue=(AValue=randomno*5.0)+(Bvalue=randomno*30.0));/*((randomno/4294967296.0)));*/
             
           /*Trying out a Linear Conguential Generator with doubles...*/
            randomno = 2.0*randomno+(0.0);
            /*randomno = getRemainder(randomno, DIVISIONNUMBER);>/* /*Was testing a random
                                                                   function found on the
                                                                   internet...*/
            
            /*(randomno - (DIVISIONNUMBER * (randomno/DIVISIONNUMBER))); /*The Modulo.*/
            /*It works, but I need to make certain that the sign is correct... (or not; it seems
                                                                                to break the code...)*/
            /* if (randomno <= 0) randomno *= -1;>*/ /*Simple as that, thankfully....*/

            /*I just need to make certain to adjust the constants used for the maths,
            to allow for less regidity with systems that may expect
            a big number...*/


            /*randomno *= 4294967296.0;*/ /* junk code*/

            /*
            
              All these experiments point to the real problem being that the
              formulas for modulo are either broke for doubles, or incorrect.

              Either, I may need to invent a new pseudorandom number generator, or
              figure out a modulo code that works with doubles.

              Well, here goes...
            */

            /*randomno = randomno-DIVISIONNUMBER * (DIVISIONNUMBER/randomno); Older version of algorithm
                                                                              kept for documentation's
                                                                              sake, this time...*/

            /* It appears that the real problem would be the fact that the division gets too small
               for any real equations to show, not that modulo operations can't be done.

               Going to need to figure out a way around that...

               When DIVISIONNUMBER and randomno are swapped within the division, the random
               number generator ALMOST works; it just has a tendency to unnessessarily climb upwards
               onto big numbers, something integers do not naturally do as the result of them
               having much lesser capabilities.

               I'm going to presume this is why "floor" is part of the official formula for modulo.

               Going to need to figure out a way to implement something similar...
            */
           
            randomno = makeshiftAbs(randomno-DIVISIONNUMBER * (DIVISIONNUMBER/randomno));

            /* And, of course, I was thinking too hard over how I can implement a rounding ability...*/

            while (randomno >= 100000) /*randonly chosen decimal place, of course*/           
              randomno -= (100000); 

            /* There we go; floating-point version of algorithm perfected; sheesh, that was harsh.
               Took me several hours to figure out! At least I've learnt a thing or two about
               software development through this, however, so hopefully, I would be more prepared
               and more ready to do it, allowing me to be faster; I value speed, after all.

               ***********************************************************************************/

            /*Testing...*/

            /*printf("%f.0\t%f.0\t%f.0\t%f.0", AValue, Bvalue, cValue, randomno);*/ /* More junk code*/
            printf("%f.0",randomno);

            getchar(); /* Makes the number more easier to see...*/
        }
    return 0;
    }