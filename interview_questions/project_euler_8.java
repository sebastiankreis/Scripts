public int sum(int number){
    int[] xs = new int[5];
    int count = 0;
    int sum = 0;

    while(number > 0){
       int digit = number % 10;

       if(digit == 0){
            sum = 0;
            count = 0;
       }

       if( count < 5 ){
          xs[count] = digit;
          count++;
       }else{
            int p0 = xs[0] * xs[1];
            int p1 = xs[2] * p0;
            int p2 = xs[3] * p1;
            int p3 = xs[4] * p2;

            if(p3 > sum)
                sum = p3;
            else{
                xs[0] = xs[1];
                xs[1] = xs[2];
                xs[2] = xs[3];
                xs[3] = xs[4];
                xs[4] = digit;
            }
       }
    }
}
