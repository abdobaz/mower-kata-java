package com.socgen.mowit;

import com.socgen.mowit.domain.EnumDirection;
import com.socgen.mowit.domain.Lawn;
import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.MowerPosition;
import com.socgen.mowit.domain.converter.CommandConverter;
import com.socgen.mowit.domain.port.MowerPrinterPort;
import com.socgen.mowit.domain.services.MowerControl;
import com.socgen.mowit.infrastructure.adapter.MowerPrinterAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    private static final MowerPrinterPort mowerPrinterPort = new MowerPrinterAdapter();
    private static final CommandConverter commandConverter = new CommandConverter();
    public static final String SPACE_DELIMITER = " ";

    public static void main(String[] args) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new File(args[0]))) {
           var lawnSize = scanner.nextLine()
                    .transform(s -> Arrays.stream(s.split(SPACE_DELIMITER)).map(Integer::parseInt))
                    .toList();

            var lawn = new Lawn(lawnSize.get(0),lawnSize.get(1));

            var mowerControl = new MowerControl(lawn, commandConverter,mowerPrinterPort);

            while (scanner.hasNext()) {
                var initialPosition = scanner.nextLine()
                        .transform(s -> s.split(SPACE_DELIMITER));

                var mowerPosition = new MowerPosition(Integer.parseInt(initialPosition[0]),Integer.parseInt(initialPosition[1]));
                var mower = new Mower(mowerPosition, EnumDirection.getByCode(initialPosition[2]));

                // execute command
                var command = scanner.nextLine();
                mowerControl.execute(mower, command);

                // print Mower information
                mowerControl.printMowerInformation(mower);
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }
}