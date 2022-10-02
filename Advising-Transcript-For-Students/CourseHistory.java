import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class CourseHistory
{
    private ArrayList<CompletedCourse> courseList; //declare an arrayList named courseList of CompletedCourse object
    private ArrayList<CompletedCourse> sortedCourse; //declare an arrayList named sortedCourse of CompletedCourse object
    
    public CourseHistory()
    {  courseList = new ArrayList<CompletedCourse>();  
       sortedCourse = new ArrayList<CompletedCourse>();   
       String department;
       String courseNumber;
       String semester;
       String credit;
       String grade;
       String competency;
       String distributionArea;
       
       
        
       try
       {
           FileReader reader = new FileReader("finishedcourses.txt");
           Scanner in = new Scanner(reader);
       
           while(in.hasNextLine())   
           {  department = in.nextLine();  
              courseNumber = in.nextLine();
              semester = in.nextLine();
              credit = in.nextLine(); 
              grade = in.nextLine();
              competency = in.nextLine();
              distributionArea = in.nextLine();  
              CompletedCourse theCourse = new CompletedCourse(department, courseNumber, semester, credit, grade, competency, distributionArea);
              courseList.add(theCourse); //adding cells to the ArrayList
              sortedCourse.add(theCourse); //adding cells to the ArrayList
          }
          in.close();  
       } catch (IOException exception)
       {
          System.out.println("Error processing file: " + exception);
       }   
    }
    
    /*This method displays the whole course history with its department, courseNumber, semester, credit, grade, competency and 
     * distirbutionArea respectively. It uses a for loop to display all the courses accordingly to the order of courses in the ArrayList 
       of CompletedCourses object.*/   
    public void displayCourseHistory()
    {  
        System.out.println("***Course History***");
        
        for(int i=0; i< courseList.size(); i++)
        {   
            courseList.get(i).displayCourse();    
        }
    }
    
    /*This method displays the total credits earned and the final GPA. The credits was a double because there are credits that are 0.5 or 0.25,
       which are decimal in nature. The total grade was computed by multiplying the credit and grade in the object and using a compound addition
       statement to sum it all. Finally, the GPA was computed by dividing the grade by the earned credits.*/
    public void summaryReport(){
        double credits = 0;
        double grade = 0;
        double GPA = 0;
        
        
        for(int i=0; i<courseList.size(); i++){
            credits += courseList.get(i).getCredit(); //this statement add the total credits using a compound addition assignment operator
            grade += (courseList.get(i).getCredit()* courseList.get(i).getGrade()); 
            //this statement add the total grade using a compound addition assignment operator by multiplying the credit and grade of the same cell
        }
        
        GPA = grade/credits; //computes the final GPA by dividing the grade by credits
        
        System.out.println("***Summary Report***");
        System.out.println("Total Credits: " + credits); //displays the total credits
        System.out.println("Final GPA: " + GPA); //displays the final GPA
    }
    
    /*This method displays a report that shows the user’s status toward meeting distribution area requirements; Arts and Humanities (AH), Social
       Science (SS), Science and Mathematics (SM), Language (LA). Inside the method, it call a private method called distDisplay - in this way,
       we can segreagte the Distribution Area Report respectively*/
    public void distAreaReport(){
        System.out.println("***Distribution Area Report***");
        distDisplay("AH"); //calls the method distDisplay and will display all courses with AH distant area
        distDisplay("SS"); //calls the method distDisplay and will display all courses with SS distant area
        distDisplay("SM"); //calls the method distDisplay and will display all courses with SM distant area
        distDisplay("LA"); //calls the method distDisplay and will display all courses with LA distant area
    }
    
    /*This method is a private method that segregates the courses according to their distantArea and get the earned credits per distantArea
       respectively. It uses an if-statement with compound conditions that if the String matches the distribution area and the grade is greater than
       zero, it means that course is credited to that certain distribution Area.*/
    private void distDisplay(String dist){
        int count = 0;
        
        for (int i=0; i<courseList.size(); i++){
            if ((courseList.get(i).getDistArea().equals(dist)) && (courseList.get(i).getGrade()>0)){
                courseList.get(i).displayCourse();
                count += courseList.get(i).getCredit();
            }
        }
        System.out.println("Total Credit/s Earned: " + count);
    }
    
    /*This method displays a report that shows the user’s status toward meeting competency (W, Q, S) requirements. If all the competencies has
       a zero value, the method displays "No competencies Completed". If one the competencies has a value greater than zero, this method displays
       "Competencies Partially Completed" and will show competencies are complete and incomplete respectively. If all the competencies are greater
       than zero, the method displays "All Competencies Completed".*/
    public void compReport(){
        int qcourse = 0;
        int wcourse = 0;
        int scourse = 0;
        
        System.out.println("***Competency Report***");
        
        for(int i=0; i<courseList.size(); i++){ //gets the count of courses separately for (W, Q, S)
            if (courseList.get(i).getCompetency().equals("Q")){
                qcourse++;
            }
            else if (courseList.get(i).getCompetency().equals("W")){
                wcourse++;
            }
            else if (courseList.get(i).getCompetency().equals("S")){
                scourse++;
            }
        }
        
        if (qcourse == 0 && wcourse == 0 && scourse == 0){
             System.out.println("No Competencies Completed"); //displayed if qcourse & wcourse & scourse are all equal to 0
        }
        else if (qcourse == 0 || wcourse == 0 || scourse == 0){
            System.out.println("Comptencies Partially Completed"); //displayed if either qcourse & wcourse & scourse are nto equal to 0
            
            if(qcourse > 0){ //qcourse is greater than 0, Q is displayed as completed; if not, Q is displayed as Incomplete
                System.out.println("Q: Complete");              
            }
            else{
                System.out.println("Q: Incomplete");   
            }
            
            if(wcourse > 0){ //wcourse is greater than 0, W is displayed as completed; if not, W is displayed as Incomplete
                System.out.println("W: Complete");              
            }
            else{
                System.out.println("W: Incomplete");   
            }
            
            if(scourse > 0){ //scourse is greater than 0, S is displayed as completed; if not, S is displayed as Incomplete
                System.out.println("S: Complete");              
            }
            else{
                System.out.println("S: Incomplete");   
            }
        }else System.out.println("Competencies Completed"); //displayed all competencies are completed
    }

    /*this method displays a full report by calling the methods summaryReport() - displays the total credit earned and final GPA, distAreaReport()
     * - displays the user's status towards completing the distribution requirement (AH, SS, SM, LA), compReport() - displays the user's status towards 
     * completing the competnecy requirement (W, Q, S)*/    
    public void fullReport(){
        System.out.println("***Full Report***");
        summaryReport();
        distAreaReport();
        compReport();
    }
    
    /*This method displays a list of all the courses in the ArrayList, such that the courses are sorted by GPA (from highest GPA to lowest GPA).
       This method calls the private method swap, which swaps the GPA of the cells in the ArrayList whenever it satisfies the if-statement 
       (that if the grade from a higher index cell is higher than a lower index cell.*/
    public void sortListGPA(){ 
        int maxIndex;
        
        for (int i=0; i<sortedCourse.size()-1; i++){
            maxIndex = i;
            for (int k=i+1; k<sortedCourse.size(); k++){
                if(sortedCourse.get(k).getGrade() > sortedCourse.get(maxIndex).getGrade()){
                    maxIndex = k;
                }
            }
            swap(i,maxIndex); //calls the private swap method
        }
        
        for (int i=0; i<sortedCourse.size(); i++){
            sortedCourse.get(i).displayCourse();
        }
    }
    
    //This private method swaps the GPA of the cells in the ArrayList to arrange it from highest to lowest
    private void swap(int i, int k){
        CompletedCourse temp = sortedCourse.get(i);
        sortedCourse.set(i, sortedCourse.get(k));
        sortedCourse.set(k, temp);
    }
}












