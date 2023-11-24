package example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockData {
    private String symbol;
    private Double LTP;
    private Double CHNG;
    private String volume;
//    private List<String>list;

    public StockData(String symbol, Double LTP, Double CHNG, String volume) {
        this.symbol = symbol;
        this.LTP = LTP;
        this.CHNG = CHNG;
        this.volume = volume;
//        this.list = new ArrayList<>(list);
    }
}
