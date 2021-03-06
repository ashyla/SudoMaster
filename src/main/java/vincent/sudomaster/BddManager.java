package vincent.sudomaster;

import android.app.Activity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class BddManager{

    private Activity levelActivity;
    private DatabaseSudoku databaseSudoku;

    public BddManager(Activity levelActivity) {
        this.levelActivity = levelActivity;
        databaseSudoku = new DatabaseSudoku(levelActivity);
    }

    public MonAdapteur getCorrectObject(String idOfObject) {

        ArrayList<SudokuGrid> categories = new ArrayList();

        switch (idOfObject) {

            case "Niveau Facile":
                categories = this.getStringList(1);
                break;

            case "Niveau Moyen":
                categories = this.getStringList(2);
                break;

            case "Niveau Difficile":
                categories = this.getStringList(3);
                break;

            default:
                categories = this.getStringList(0);
                break;
        }

        MonAdapteur dataAdapter = new MonAdapteur(this.levelActivity, categories);
        return dataAdapter;
    }


    public ArrayList<SudokuGrid> getStringList(int bddInfo) {
        return databaseSudoku.getGridsByLevel(bddInfo);
    }

    public void constructDatabase(){
        TextView counter = (TextView) levelActivity.findViewById(R.id.countDbLoading);
        for(int x = 1; x<4;x++) {
            int ressourceName ;
            switch (x){
                case 0:
                    ressourceName = R.raw.level_0;
                    break;
                case 1:
                    ressourceName = R.raw.level_1;
                    break;
                case 2:
                    ressourceName = R.raw.level_2;
                    break;
                default:
                    ressourceName = R.raw.level_0;
                    break;
            }

            InputStream is = levelActivity.getResources().openRawResource(ressourceName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String str = new String();
            String buf = str;
            String playerGrid = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";

            if (is != null) {
                try {
                    int index = 0;
                    while ((str = reader.readLine()) != null) {
                        if(index<=1000) {
                            /*if(index%100==0){
                                Log.d("show index Number", index+"");
                            }*/
                            SudokuGrid sudokuGrid = new SudokuGrid((x * 9 + index), x, index, 0, str);
                            sudokuGrid.setPlayerGrid(playerGrid);
                            databaseSudoku.ajouter(sudokuGrid);
                        }
                        index++;
                    }
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        counter.setText("La base de donnée est chargée");
    }

}
