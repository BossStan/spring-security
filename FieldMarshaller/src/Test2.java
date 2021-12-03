import java.sql.*;

public class Test2 {
    // Format a string so that it has the specified width.
    private static String format (String s, int width)
    {
        String formattedString;

        // The string is shorter than specified width,
        // so we need to pad with blanks.
        if (s.length() < width) {
            StringBuffer buffer = new StringBuffer (s);
            for (int i = s.length(); i < width; ++i)
                buffer.append (" ");
            formattedString = buffer.toString();
        }

        // Otherwise, we need to truncate the string.
        else
            formattedString = s.substring (0, width);

        return formattedString;
    }



    public static void main (String[] parameters)
    {
        // Check the input parameters.
        if (parameters.length != 3) {
            System.out.println("");
            System.out.println("Usage:");
            System.out.println("");
            System.out.println("   JDBCQuery system collectionName tableName");
            System.out.println("");
            System.out.println("");
            System.out.println("For example:");
            System.out.println("");
            System.out.println("");
            System.out.println("   JDBCQuery mySystem qiws qcustcdt");
            System.out.println("");
            return;
        }

        String system           = parameters[0];
        String collectionName   = parameters[1];
        String tableName        = parameters[2];

        try (Connection connection = DriverManager.getConnection("jdbc:as400://" + system)) {

            // Load the IBM Toolbox for Java JDBC driver.
//            DriverManager.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());

            // Get a connection to the database.  Since we do not
            // provide a user id or password, a prompt will appear.
            DatabaseMetaData dmd = connection.getMetaData();

            // Execute the query.
            Statement select = connection.createStatement();
            ResultSet rs = select.executeQuery(
                    "SELECT * FROM " + collectionName + dmd.getCatalogSeparator() + tableName);

            // Get information about the result set.  Set the column
            // width to whichever is longer: the length of the label
            // or the length of the data.
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            String[] columnLabels = new String[columnCount];
            int[] columnWidths = new int[columnCount];
            for (int i = 1; i <= columnCount; ++i) {
                columnLabels[i - 1] = rsmd.getColumnLabel(i);
                columnWidths[i - 1] = Math.max(columnLabels[i - 1].length(), rsmd.getColumnDisplaySize(i));
            }

            // Output the column headings.
            for (int i = 1; i <= columnCount; ++i) {
                System.out.print(format(rsmd.getColumnLabel(i), columnWidths[i - 1]));
                System.out.print(" ");
            }
            System.out.println();

            // Output a dashed line.
            StringBuffer dashedLine;
            for (int i = 1; i <= columnCount; ++i) {
                for (int j = 1; j <= columnWidths[i - 1]; ++j)
                    System.out.print("-");
                System.out.print(" ");
            }
            System.out.println();

            // Iterate throught the rows in the result set and output
            // the columns for each row.
            while (rs.next()) {
                for (int i = 1; i <= columnCount; ++i) {
                    String value = rs.getString(i);
                    if (rs.wasNull())
                        value = "<null>";
                    System.out.print(format(value, columnWidths[i - 1]));
                    System.out.print(" ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println();
            System.out.println("ERROR: " + e.getMessage());
        }

        // Clean up.
        // Ignore.

        System.exit (0);
    }

}
