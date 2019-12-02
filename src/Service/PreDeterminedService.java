package Service;

import Controller.StaffControl;

import java.io.*;

public class PreDeterminedService {
    public void readDataFromFile() throws IOException {
        StaffControl staffControl = StaffControl.getInstance();
        File file = new File("src/FileData/staffs.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int num = Integer.parseInt(br.readLine());

        for(int i = 0; i < num; i++) {
            String[] idAndName = br.readLine().split(",");
            String id = idAndName[0];
            String name = idAndName[1];
            String address = br.readLine();
            staffControl.addStaff(Integer.parseInt(id), name, address);
        }
        staffControl.saveData();
        br.close();
        fr.close();
    }

    public Boolean checkEmptyData(){
        try {
            FileInputStream fis = new FileInputStream("src/FileData/staffData.ser");
            int empty = fis.available();
            if(empty == 0){
                return Boolean.FALSE;
            }
            return Boolean.TRUE;

        }catch (FileNotFoundException e){
            e.printStackTrace();
            return Boolean.FALSE;
        }catch (IOException e){
            e.printStackTrace();
            return Boolean.FALSE;
        }

    }
}
