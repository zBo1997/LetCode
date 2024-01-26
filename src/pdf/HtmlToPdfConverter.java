package pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

public class HtmlToPdfConverter {
    public static void main(String[] args) {
        String htmlContent = "<p><span style=\"letter-spacing:2px\">${appointee} 先生/女士：</span></p><p style=\"text-indent:2em;\" size=\"0\" _root=\"undefined\" __ownerid=\"undefined\" __hash=\"undefined\" __altered=\"false\"><span style=\"line-height:2\"> 您好！</span><br/><span style=\"line-height:2\">   很荣幸地通知您，您已通过我公司面试程序，即将正式成为公司的一员。请您仔细阅读以下内容： </span><br/><span style=\"font-size:14px\"><strong><span style=\"line-height:2.5\"> 一、您的任职信息：</span></strong></span><br/><span style=\"line-height:2\">        任职部门：${organizationName}</span> <br/><span style=\"line-height:2\">        任职岗位：${position}</span><br/><span style=\"line-height:2\">   服务期限：劳动合同自您入职之日起，为期${employYear} ；其中包括试用期${probationPeriodNumber}； </span><br/><span style=\"font-size:14px\"><strong><span style=\"line-height:2.5\"> 二、薪资构成：    </span></strong></span><br/><span style=\"line-height:2\">        税前固定月薪：${baseSalaryAmt}；</span><br/><span style=\"font-size:14px\"><strong><span style=\"line-height:2.5\">三 、入职时间：</span></strong></span><br/><span style=\"line-height:2\">        建议您于${suggestedEntryTime}  09：00，到${workAddress}人力行政中心办理报到手续。</span><br/><span style=\"font-size:14px\"><strong><span style=\"line-height:2.5\">四、入职材料：</span></strong></span><br/><span style=\"line-height:2\">      1、原单位离职证明原件1份（加盖原单位印章）；</span><br/><span style=\"line-height:2\">      2、二代身份证原件及复印件2份；</span><br/><span style=\"line-height:2\">      3、最高学历证书及学位证原件及复印件1份；</span><br/><span style=\"line-height:2\">      4、相关资格证书原件及复印件1份；</span><br/><span style=\"line-height:2\">      5、提供原公司公积金账号、公司名称、个人公积金账号（原单位有缴纳公积金的）；</span><br/><span style=\"line-height:2\">      6、银行卡原件及复印件1份，复印件上注明姓名、账号、开户行（用于工资发放）；</span><br/><span style=\"line-height:2\">      7、提供近期3个月正规三甲医院入职体检报告一份。</span><br/><span style=\"line-height:2\">      请保证提供以上资料的真实性和可靠性，公司需复核原件后仅保留证件复印件，原件最迟不超过3个工作日退还本人。</span><br/><span style=\"font-size:14px\"><strong><span style=\"line-height:2.5\">五、其他信息：</span></strong></span><br/><span style=\"line-height:2\">      上下班时间：上午09：00-12：00    下午13：00-18：00 ；</span><br/><span style=\"line-height:2\">      五天8小时制，周末双休，享有法定节假日；</span><br/><span style=\"line-height:2\">      根据国家及地方法律法规，入职1个月内，公司将根据相关法定标准结合公司政策，缴纳五险一金；</span><br/><span style=\"font-size:14px\"><strong><span style=\"line-height:2.5\">六、通知书失效的情况：</span></strong></span><br/><span style=\"line-height:2\">      您在收到本通知后，未能在 ${employDeadline} 小时内在当前页面确认接受，本通知失效；</span><br/><span style=\"line-height:2\">      您在应聘过程中，提供虚假的个人信息、就职经历，一经核实的，本通知失效；</span><br/><span style=\"line-height:2\">      您在入职后，提供虚假的个人入职材料，一经核实的，本通知失效；</span><br/><span style=\"line-height:2\">      您在入职前，刻意隐瞒身体健康状态，患有重大疾病，将严重影响工作效率的，本通知失效；</span><br/><span style=\"line-height:2\">      您与原用人单位存在竞业限制约定且在竞业限制范围之内的，本通知失效；</span><br/><span style=\"line-height:2\">      您未能在${suggestedEntryTime}  09：00前到岗，且未提前与人力行政中心取得联系，或未能提供合理的延迟到岗原因的，本通知失效；</span><br/><span style=\"line-height:2\">      以上由于您个人原因导致的通知失效，如对公司经营或工作开展造成重大损失的，公司有权保留追究的权利。</span><br/><span style=\"font-size:14px\"><strong><span style=\"line-height:2.5\">七、特别说明：</span></strong></span><br/><span style=\"line-height:2\">      本通知书中所列收入均为税前标准，公司将按照国家有关法律规定从支付给您的所有款项中进行必要的代扣代缴， 包括但不限于社会保险，公积金和个人所得税。      </span><br/> </p>";
        String pdfFilePath = "output.pdf";
        htmlToPdf(htmlContent,pdfFilePath);
    }

    /**
     * html转成pdf
     *
     * @param html          html
     * @param outputPdfPath 输出pdf路径
     */
    private static void htmlToPdf(String html, String outputPdfPath) {
        com.itextpdf.text.Document document = null;
        try {
            // 纸
            document = new com.itextpdf.text.Document(PageSize.A4);
            // 笔
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPdfPath));
            document.open();
            // html转pdf
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(html.getBytes()),
                    Charset.forName("UTF-8"), new FontProvider() {
                        @Override
                        public boolean isRegistered(String s) {
                            return false;
                        }

                        @Override
                        public Font getFont(String s, String s1, boolean embedded, float size, int style, BaseColor baseColor) {
                            // 配置字体
                            Font font = null;
                            try {
                                // 方案一：使用本地字体(本地需要有字体)
//                              BaseFont bf = BaseFont.createFont("c:/Windows/Fonts/simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                                // 方案二：使用jar包：iTextAsian，这样只需一个jar包就可以了
                                BaseFont bf = BaseFont.createFont("C:\\\\Windows\\\\Fonts\\\\STSONG.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                                font = new Font(bf, size, style, baseColor);
                                font.setColor(baseColor);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return font;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

}

