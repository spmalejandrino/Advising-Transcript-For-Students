public class New
{
    public boolean negativeRows(double[][] a, final int ROW, final int COLUMN){
        int count = 0;
        int finalcount = 0;
        
        for(int r=0; r<ROW; r++){
            for(int c=0; c<COLUMN; c++){
                if(a[r][c] < 0){
                    count++;
                }
            }if(count > 0){
                finalcount++;
                count = 0;
            }
        }
        if(finalcount == ROW){
            return true;
        }else return false;
    }
}
