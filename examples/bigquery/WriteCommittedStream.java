import com.google.api.core.ApiFuture;
import com.google.cloud.bigquery.storage.v1.AppendRowsResponse;
import com.google.cloud.bigquery.storage.v1.BigQueryWriteClient;
import com.google.cloud.bigquery.storage.v1.CreateWriteStreamRequest;
import com.google.cloud.bigquery.storage.v1.FinalizeWriteStreamRequest;
import com.google.cloud.bigquery.storage.v1.JsonStreamWriter;
import com.google.cloud.bigquery.storage.v1.TableName;
import com.google.cloud.bigquery.storage.v1.WriteStream;
import com.google.protobuf.Descriptors.DescriptorValidationException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONObject;

public class WriteCommittedStream {

  public static void runWriteCommittedStream()
      throws DescriptorValidationException, InterruptedException, IOException {
    // TODO(developer): Replace these variables before running the sample.
    String projectId = "MY_PROJECT_ID";
    String datasetName = "MY_DATASET_NAME";
    String tableName = "MY_TABLE_NAME";

    writeCommittedStream(projectId, datasetName, tableName);
  }

  public static void writeCommittedStream(String projectId, String datasetName, String tableName)
      throws DescriptorValidationException, InterruptedException, IOException {

    try (BigQueryWriteClient client = BigQueryWriteClient.create()) {
      // Initialize a write stream for the specified table.
      // For more information on WriteStream.Type, see:
      // https://googleapis.dev/java/google-cloud-bigquerystorage/latest/com/google/cloud/bigquery/storage/v1beta2/WriteStream.Type.html
      WriteStream stream = WriteStream.newBuilder().setType(WriteStream.Type.COMMITTED).build();
      TableName parentTable = TableName.of(projectId, datasetName, tableName);
      CreateWriteStreamRequest createWriteStreamRequest =
          CreateWriteStreamRequest.newBuilder()
              .setParent(parentTable.toString())
              .setWriteStream(stream)
              .build();
      WriteStream writeStream = client.createWriteStream(createWriteStreamRequest);

      // Use the JSON stream writer to send records in JSON format.
      // For more information about JsonStreamWriter, see:
      // https://googleapis.dev/java/google-cloud-bigquerystorage/latest/com/google/cloud/bigquery/storage/v1beta2/JsonStreamWriter.html
      try (JsonStreamWriter writer =
          JsonStreamWriter.newBuilder(writeStream.getName(), writeStream.getTableSchema())
              .build()) {
        // Write two batches to the stream, each with 10 JSON records. A writer should be
        // used for as much writes as possible. Creating a writer for just one write is an
        // antipattern.
        for (int i = 0; i < 2; i++) {
          // Create a JSON object that is compatible with the table schema.
          JSONArray jsonArr = new JSONArray();
          for (int j = 0; j < 10; j++) {
            JSONObject record = new JSONObject();
            record.put("col1", String.format("record %03d-%03d", i, j));
            jsonArr.put(record);
          }

          // To detect duplicate records, pass the index as the record offset.
          // To disable deduplication, omit the offset or use WriteStream.Type.DEFAULT.
          ApiFuture<AppendRowsResponse> future = writer.append(jsonArr, /*offset=*/ i * 10);
          AppendRowsResponse response = future.get();
        }
        // Finalize the stream after use.
        FinalizeWriteStreamRequest finalizeWriteStreamRequest =
            FinalizeWriteStreamRequest.newBuilder().setName(writeStream.getName()).build();
        client.finalizeWriteStream(finalizeWriteStreamRequest);
      }
      System.out.println("Appended records successfully.");
    } catch (ExecutionException e) {
      // If the wrapped exception is a StatusRuntimeException, check the state of the operation.
      // If the state is INTERNAL, CANCELLED, or ABORTED, you can retry. For more information, see:
      // https://grpc.github.io/grpc-java/javadoc/io/grpc/StatusRuntimeException.html
      System.out.println("Failed to append records. \n" + e.toString());
    }
  }
}
