package com.example.DentalClinicMVC.dao;

import com.example.DentalClinicMVC.model.Address;
import com.example.DentalClinicMVC.model.Patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PatientDao implements IDao<Patient>{
    private static final String SQL_INSERT = "INSERT INTO PATIENTS(NAME, LASTNAME, CARD_IDENTITY, ADMISSION_OF_DATE,ADDRESS_ID,EMAIL) VALUES (?,?,?,?,?,?)";
    private static final String SQL_SELECT_ID = "SELECT * FROM PATIENTS WHERE ID = ?";
    private static final String SQL_UPDATE = "UPDATE PATIENTS SET NAME=?, LAST_NAME=?, CARD_IDENTITY=?, ADMISSION_OF_DATE=?,ADDRESS_ID=?,EMAIL=?  WHERE ID =?";
    private static final String SQL_DELETE = "DELETE * FROM PATIENTS WHERE ID=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM PATIENTS";
    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM PATIENTS WHERE EMAIL=?";
    @Override
    public Patient save(Patient patient) {
        Connection connection = null;
        try {
            AddressDao addressDao = new AddressDao();
            addressDao.save(patient.getAddress());

            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,patient.getName());
            ps.setString(2,patient.getLastName());
            ps.setInt(3,patient.getCardIdentity());
            ps.setDate(4, Date.valueOf(patient.getAdmissionOfDate()));
            ps.setInt(5,patient.getAddress().getId());
            ps.setString(6,patient.getEmail());

            ResultSet rs = ps.getResultSet();

            while (rs.next()){
                patient.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patient;
    }
    @Override
    public Patient findById(Integer id) {
        Connection connection = null;

        Address address = null;

        Patient patient = null;

        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ID);
            ps.setInt(1,id);

            ResultSet rs =  ps.executeQuery();

            AddressDao addressDao = new AddressDao();
            while (rs.next()){
                address = addressDao.findById(rs.getInt(6));
                patient = new Patient(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5).toLocalDate(),
                        address,
                        rs.getString(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patient;
    }
    @Override
    public void update(Patient patient) {
        Connection connection = null;
        try {
            connection = DB.getConnection();

            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1,patient.getName());
            ps.setString(2,patient.getLastName());
            ps.setInt(3,patient.getCardIdentity());
            ps.setDate(4,Date.valueOf(patient.getAdmissionOfDate()));
            ps.setInt(5,patient.getAddress().getId());
            ps.setString(6,patient.getEmail());
            ps.setInt(7,patient.getId());


            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void delete(Integer id) {
        Connection connection = null;
        try {
            connection = DB.getConnection();

            PreparedStatement ps = connection.prepareStatement(SQL_DELETE);
            ps.setInt(1,id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public List<Patient> findAll() {
        Connection connection = null;
        List<Patient> patientList = null;
        Patient patient = null;
        Address address = null;

        try {
            AddressDao addressDao = new AddressDao();
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                address = addressDao.findById(rs.getInt(6));
                patient = new Patient(rs.getInt(1),rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5).toLocalDate(),
                        address,
                        rs.getString(7));
                patientList.add(patient);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patientList;
    }
    @Override
    public Patient findByString(String string) {
        Connection connection = null;

        Address address = null;

        Patient patient = null;

        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_EMAIL);
            ps.setString(1,string);

            ResultSet rs =  ps.executeQuery();

            AddressDao addressDao = new AddressDao();

            while (rs.next()){
                address = addressDao.findById(rs.getInt(6));
                patient = new Patient(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5).toLocalDate(),
                        address,
                        rs.getString(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patient;
    }
}
