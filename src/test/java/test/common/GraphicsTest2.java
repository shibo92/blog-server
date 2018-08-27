package test.common;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
public class GraphicsTest2 {
    /** 此处设置灰度字符，此处只用十个字符，可以设置更多 */
    private static char[] cs = new char[] { '.', ',', '*', '+', '=', '&', '$', '@', '#', ' ' };
    public static void main(String[] args) throws IOException {
// 读取图片
        BufferedImage bfedimage = ImageIO.read(new File("D:\\header2.png"));
        // 图片转字符串后的数组
        char[][] css = new char[bfedimage.getWidth()][bfedimage.getHeight()];
        for (int x = 0; x < bfedimage.getWidth(); x++) {
            for (int y = 0; y < bfedimage.getHeight(); y++) {
                int rgb = bfedimage.getRGB(x, y);
                Color c = new Color(rgb);
                // 得到灰度值
                int cc = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                css[x][y] = cs[(int) ((cc * 10 - 1) / 255)];
            }
        }
        // 取得模板HTML
        String temp = readFile(new File("D:\\temp.html"),"gbk");
        StringBuffer sb = new StringBuffer();
        // 开始拼接内容
        for (int y = 0; y < css[0].length; y++) {
            for (int x = 0; x < css.length; x++) {
                sb.append(css[x][y]);
            }
            sb.append("\r\n");
        }
        System.out.println(sb.toString());
        // 生成文件
        String content = toHTML(sb.toString());
        String filecontent = replaceStrAllNotBack(temp, "${content}", content);
        writeFile(new File("D:\\content.html"), filecontent, "gbk");
    }
    public static String toHTML(String s) {
        s = s.replaceAll("&", "&");
        s = s.replaceAll(" ", " ");
        s = s.replaceAll(">", ">");
        s = s.replaceAll("<", "<");
        s = s.replaceAll("\"", "\"");
                s = s.replaceAll("\\\r\\\n", "<br/>");
        s = s.replaceAll("\\\r", "<br/>");
        s = s.replaceAll("\\\n", "<br/>");
        return s;
    }
    public static String replaceStrAllNotBack(String str, String strSrc, String strDes) {
        StringBuffer sb = new StringBuffer(str);
        int index = 0;
        while ((index = sb.indexOf(strSrc, index)) != -1) {
            sb.replace(index, index + strSrc.length(), strDes);
            index += strDes.length();
        }
        return sb.toString();
    }
    /**
     * 读文件(使用默认编码)
     *
     * @param file
     * @return 文件内容
     * @throws IOException
     */
    public static String readFile(File file, String charset) throws IOException {
        InputStreamReader fr = new InputStreamReader(new FileInputStream(file), charset);
        StringBuffer sb = new StringBuffer();
        char[] bs = new char[1024];
        int i = 0;
        while ((i = fr.read(bs)) != -1) {
            sb.append(bs, 0, i);
        }
        fr.close();
        return sb.toString();
    }
    /**
     * 写文件
     *
     * @param file
     * @param string
     * 字符串
     * @param encoding
     * 编码
     * @return 文件大小
     * @throws IOException
     */
    public static int writeFile(File file, String string, String encoding) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        try {
            byte[] bs = string.getBytes(encoding);
            fos.write(bs);
            return bs.length;
        } finally {
            fos.close();
        }
    }
}