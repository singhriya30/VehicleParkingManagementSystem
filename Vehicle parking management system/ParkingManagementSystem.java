import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.*;

public class ParkingManagementSystem {
    private final HashMap<String, Vehicle> parkedVehicles = new HashMap<>();
    private final int capacity;
    private JFrame frame;
    private JTextField licenseField;
    private JTextField typeField;
    private JTextArea displayArea;

    public ParkingManagementSystem(int capacity) {
        this.capacity = capacity;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Parking Management System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel licenseLabel = new JLabel("License Plate:");
        licenseLabel.setBounds(10, 10, 120, 25);
        frame.add(licenseLabel);

        licenseField = new JTextField();
        licenseField.setBounds(140, 10, 200, 25);
        frame.add(licenseField);

        JLabel typeLabel = new JLabel("Vehicle Type:");
        typeLabel.setBounds(10, 40, 120, 25);
        frame.add(typeLabel);

        typeField = new JTextField();
        typeField.setBounds(140, 40, 200, 25);
        frame.add(typeField);

        JButton parkButton = new JButton("Park Vehicle");
        parkButton.setBounds(10, 80, 150, 25);
        parkButton.addActionListener((ActionEvent e) -> {
            String license = licenseField.getText();
            String type = typeField.getText();
            boolean success = parkVehicle(license, type);
            displayArea.append(success ? "Parked: " + license + "\n" : "Parking Full!\n");
        });
        frame.add(parkButton);

        JButton removeButton = new JButton("Remove Vehicle");
        removeButton.setBounds(180, 80, 150, 25);
        removeButton.addActionListener((ActionEvent e) -> {
            String license = licenseField.getText();
            Vehicle vehicle = removeVehicle(license);
            displayArea.append(vehicle != null ? "Removed: " + license + "\n" : "Vehicle not found!\n");
        });
        frame.add(removeButton);

        displayArea = new JTextArea();
        displayArea.setBounds(10, 120, 350, 200);
        frame.add(displayArea);

        frame.setVisible(true);
    }

    private boolean parkVehicle(String licensePlate, String vehicleType) {
        if (parkedVehicles.size() < capacity) {
            Vehicle vehicle = new Vehicle(licensePlate, vehicleType);
            parkedVehicles.put(licensePlate, vehicle);
            return true;
        }
        return false;
    }

    private Vehicle removeVehicle(String licensePlate) {
        return parkedVehicles.remove(licensePlate);
    }

    public static void main(String[] args) {
        new ParkingManagementSystem(5); // Example capacity
    }

    // Inner class for Vehicle
    private class Vehicle {
        private String licensePlate;
        private String vehicleType;

        public Vehicle(String licensePlate, String vehicleType) {
            this.licensePlate = licensePlate;
            this.vehicleType = vehicleType;
        }

        public String getLicensePlate() { return licensePlate; }
        public String getVehicleType() { return vehicleType; }


        private class ParkingSlot {
            private int slotNumber;
            private Vehicle vehicle;
    
            public ParkingSlot(int slotNumber) {
                this.slotNumber = slotNumber;
            }
    
            public int getSlotNumber() { return slotNumber; }
            public boolean isOccupied() { return vehicle != null; }
    
            public void parkVehicle(Vehicle vehicle) {
                this.vehicle = vehicle;
            }
    
            public Vehicle getVehicle() { return vehicle; }
    
            public void removeVehicle() {
                vehicle = null;
            }
        }
    }
    }


