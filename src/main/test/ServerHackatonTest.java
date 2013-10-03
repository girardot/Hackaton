import org.fest.assertions.Assertions;
import org.junit.Ignore;
import org.junit.Test;

public class ServerHackatonTest {

    @Ignore
    @Test
    public void test_calculate() throws Exception {
        ServerHackaton serverHackaton = new ServerHackaton();

        Assertions.assertThat(serverHackaton.calculate("combien font 0 plus 3 ?")).isEqualTo(3);
        Assertions.assertThat(serverHackaton.calculate("combien font 5 plus 3 ?")).isEqualTo(8);
        Assertions.assertThat(serverHackaton.calculate("combien font 2 moins 1 ?")).isEqualTo(1);
        Assertions.assertThat(serverHackaton.calculate("combien font 5 moins 4 ?")).isEqualTo(1);
        Assertions.assertThat(serverHackaton.calculate("combien font 0 fois 1 ?")).isEqualTo(0);
        Assertions.assertThat(serverHackaton.calculate("combien font 3 fois 5 ?")).isEqualTo(15);
    }

    @Ignore
    @Test
    public void test_quelconque() {
        System.out.println((((1.1+2)+3.14+4+(5+6+7)+(8+9+10)*4267387833344334647677634D)/2*553344300034334349999000D)/31878018903828899277492024491376690701584023926880D);
    }
}
