package vn.testmaster;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CssSelector {
    /*
    CSS Selector là một quy tắc viết các biểu locator tham chiếu đến các Element trên webpage,
    => Giúp Selenium xác định dc Element mà muốn thao tác.
    CSSSelector mạnh hơn các phương pháp locator truyền thống :ID, Name, ClassName, LinkText
    Vì:
        - Kết hợp dc >1 dấu hiệu vào để xác định locator -> chính xác hơn
        - Dựa vào mqh css với html (cơ bản để build lên ứng web) - flexible hơn (vì: chả ông dev nào
        làm web mà ko dùng css).
     */
    /*
    SYNTAX CSS-SELECTOR
    - Tag + Id. Cú pháp: tag#id value ví dụ: input#login-email
    - Tag + Class value. cú pháp: tag.class value. Ví dụ: input.FormSearch__Input-sc-1fwg3wo-2
    Chú ý: các giá trị (value) của thuộc tính class sẽ cách nhau bởi dấu cách (space).
    - Tag + Attribute: cú pháp: tag[attribute=value] ví dụ: input[type="email"]
    - Tag + ID hoặc Class hoặc Attribute hoặc kết hợp hết.
    Syntax: viết liên tiếp các quy tắc lần lượt
    ví dụ kết hợp giữa tag + id + attribute: tag#id[attribute=value]
                    tag + class + attribute: tag.class[attribute=value]
                    tag + attribute + attribute: tag[attribute=value][attribute=value]
    - Kết hợp tương đối với giá trị của thuộc tính
        syntax: tag[attribute^="value"] - tìm element có attribute chỉ định bắt đầu bằng value
                tag[attribute$="value"] - tìm element có attribute kết thúc bằng value
                tag[attribute*="value"] - tìm element có attribute chữa value (value nằm bất cứ đâu).
    Kết hợp 2 biểu thức css locator lại -> làm 1.
    - Absolute: <parent locator> > <child locator> kết hợp 2 biểu thức là cha con trực tiếp
    ví dụ: div.mod-login-input-password>input
    - Relative <parent locator> [space] <child locator> - kết hợp 2 biểu thức là cha con gián tiếp. (con, cháu, chắt)
     */
    WebDriver driver; //global driver
    @Before
    public void initTest()
    {
        System.setProperty("webdriver.chrome.driver", "/Users/lapnt/Downloads/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "/Users/lapnt/Downloads/geckodriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void Test_login_banggood()
    {
        //1. Mở banggood
        this.driver.get("https://sea.banggood.com/login.html");
        //2. Nhập username : khanh.tx@live.com
        //Sử dụng By.cssSelector để thực hiện tìm element thông qua CSS locator
        WebElement tbUsername = this.driver.findElement(By.cssSelector("input#login-email"));
        tbUsername.sendKeys("khanh.tx@live.com");
        //3. nhập password: abc123
        WebElement tbPassword = this.driver.findElement(By.cssSelector("input#login-pwd"));
        tbPassword.sendKeys("abc123");
        //4. Login
    }

    @Test
    public void Test_search_tiki()
    {
        this.driver.get("https://tiki.vn/");
        WebElement tbSearch = this.driver.findElement(By.cssSelector("input.FormSearch__Input-sc-1fwg3wo-2"));
        tbSearch.sendKeys("iphone 12 promax");
        tbSearch.sendKeys(Keys.ENTER); //Enem Keys - chứa các system keys
    }

    @Test
    public void Test_login_testmaster() {
        //Automation
        this.driver.get("http://testmaster.vn/admin");
        WebElement tbEmail = this.driver.findElement(By.cssSelector("input[type=\"email\"]"));
        tbEmail.sendKeys("khanh.tx@live.com");

        WebElement tbPassword = this.driver.findElement(By.cssSelector("input[type=password]"));
        tbPassword.sendKeys("abc1234");

        WebElement btnLogin = this.driver.findElement(By.cssSelector("div.credential button"));
        btnLogin.click();

      // Thread.sleep(1000); //Chờ 1s để hệ thống xử lý
        //Dùng tạm thôi, ko khuyến khích dùng ntn
        //Không có cái chờ này, false bởi vì selenium chạy qua và test luôn kết quả trong kết quả vẫn đang xử lý
        //chưa hiện lên UI

        //Test
        //Login mật khẩu sai -> check message xem message có đunngs.
        WebElement lbMessage = this.driver.findElement(By.cssSelector("div.body-message>b"));
        //getText() - method trả về text hiển thị trên element
        //getAttribute(attribute name) - trả ra giá trị của attribute truyền qua tham số name.

        Assert.assertEquals("Tên đăng nhập hoặc Mật khẩu không đúng.", lbMessage.getText());
        //Chú ý: Test luôn kết thúc bằng 1 assertion
    }

    @Test
    public void Test_login_lazada()
    {
        this.driver.get("https://member.lazada.vn/user/login?spm=a2o4n.home.header.d5.1905e182c1Uumj&redirect=https%3A%2F%2Fwww.lazada.vn%2F");
        WebElement tbUsername = this.driver.findElement(By.cssSelector("input[data-spm-anchor-id*=\"login_signup\"]"));
        tbUsername.sendKeys("khanh.tx@live.com");

        //Để lấy màu của text
        tbUsername.getCssValue("color"); //get màu của text trên element
        tbUsername.getCssValue("border-color"); //color hay border-color css property.
        //
        tbUsername.getAttribute("class"); //check xem có chưa value này is-error?
    }

    @Test
    public void Test_login_Fado() throws InterruptedException
    {
        this.driver.get("https://fado.vn/dang-nhap?redirect=https%3A%2F%2Ffado.vn%2F");
        //Nhập Email
        WebElement tbEmail = this.driver.findElement(By.cssSelector("input[data-test-login-email-input]"));
        tbEmail.sendKeys("lapnt@bravo.com.vn");//khanh.tx@live.com
        //Nhập password
        WebElement tbPassWord = this.driver.findElement(By.cssSelector("input[data-test-login-password]"));
        tbPassWord.sendKeys("1234");
       // Thread.sleep(1000);
        //Kích vào nút đăng nhập
        WebElement btnLogin = this.driver.findElement(By.cssSelector("button[type=\"submit\"]"));
        btnLogin.click();

       Thread.sleep(1000); //Chờ 1s để hệ thống xử lý

        //Test
        //Vui lòng nhập dữ liệu nếu như không nhập
        //Thông báo “Vui lòng nhập dữ liệu” với chữ màu đỏ, sẽ hiển thị bên dưới của các fields Email và
        //Password trong trường hợp login với blank values đồng thời border của các fields cũng được
        //chuyển sang màu đỏ. Thông báo sẽ ẩn khi người dùng input giá trị hợp lệ, đồng thời border của
        //các field cũng về trạng thái bình thường.
        WebElement lbMessage = this.driver.findElement(By.cssSelector("label.mz-form-error-label"));
        //getText() - method trả về text hiển thị trên element
        //getAttribute(attribute name) - trả ra giá trị của attribute truyền qua tham số name.
        Assert.assertEquals("Vui lòng nhập dữ liệu", lbMessage.getText());
        //Chú ý: Test luôn kết thúc bằng 1 assertion

        //Thông báo lỗi “- Tài khoản không tồn tại, vui lòng kiểm tra lại” sẽ hiển thị khi login với Email
        //không có trong hệ thống.
        WebElement lbMessage1 = this.driver.findElement(By.cssSelector("div[class*=\"my-alert -alert-danger\"]>b"));
        //getText() - method trả về text hiển thị trên element
        //getAttribute(attribute name) - trả ra giá trị của attribute truyền qua tham số name.
        Assert.assertEquals("Có lỗi xảy ra:", lbMessage1.getText());

    }


        //Để lấy màu của text
        //tbUsername.getCssValue("color"); //get màu của text trên element
        //tbUsername.getCssValue("border-color"); //color hay border-color css property.
        //
        //tbUsername.getAttribute("class"); //check xem có chưa value này is-error?

}
