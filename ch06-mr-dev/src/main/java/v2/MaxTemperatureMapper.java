package v2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * 类的实现描述:
 *
 * @author liqun.wu
 */
public class MaxTemperatureMapper
        extends Mapper<LongWritable, Text, Text, IntWritable> {

    private NcdcRecordParser parser = new NcdcRecordParser();

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        System.out.println("key: " + key);
        System.out.println("value: " + value.toString());
        parser.parse(value);
        if (parser.isValidTemperature()) {
            context.write(new Text(parser.getYear()),
                          new IntWritable(parser.getAirTemperature()));
        }
    }
}
