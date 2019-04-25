import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by xiangrui on 2019/4/23.
 *
 * @author xiangrui
 * @date 2019/4/23
 */
public class Util {



    public static void appendFileTextLine(String fileName, String content) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();// 创建新文件,有同名的文件的话直接覆盖
            }
            try (FileWriter writer = new FileWriter(file, true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.append(content);
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
