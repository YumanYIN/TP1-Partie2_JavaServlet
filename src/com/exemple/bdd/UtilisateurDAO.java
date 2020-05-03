package com.exemple.bdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.exemple.beans.Utilisateur;

public class UtilisateurDAO extends DAOContext {
    public static Boolean isValidLogin( String login, String password ) {
        try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
            // String strSql = "SELECT * FROM T_Users WHERE login='" + login +
            // "' AND password='" + password + "'";
            String strSql = "SELECT * FROM Utilisateur WHERE email=? AND mot_de_passe=?";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                statement.setString( 1, login );
                statement.setString( 2, password );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }

    public static Boolean isValidEmail( String login ) {
        try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {

            String strSql = "SELECT * FROM Utilisateur WHERE email=?";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                statement.setString( 1, login );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }

    public static void creerUtilisateur( Utilisateur utilisateur ) {
        try ( Connection connection = DriverManager.getConnection( dbURL,
                dbLogin, dbPassword ) ) {

            String strSql = "INSERT INTO Utilisateur (email, mot_de_passe, nom, isadmin, date_inscription) VALUES (?, ?, ?, ?, NOW())";
            try ( PreparedStatement statement = connection.prepareStatement(
                    strSql ) ) {
                statement.setString( 1, utilisateur.getEmail() );
                statement.setString( 2, utilisateur.getMotDePasse() );
                statement.setString( 3, utilisateur.getNom() );
                statement.setBoolean( 4, utilisateur.getAdmin() );
                statement.executeUpdate();
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }

    public static Utilisateur getAllInfo(Utilisateur utilisateur){
        try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {

            String strSql = "SELECT * FROM Utilisateur WHERE email=? AND mot_de_passe=?";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                statement.setString( 1, utilisateur.getEmail() );
                statement.setString( 2, utilisateur.getMotDePasse() );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        utilisateur.setNom(resultSet.getString("nom"));
                        utilisateur.setDateInscription(resultSet.getTimestamp("date_inscription"));
                        utilisateur.setAdmin(resultSet.getBoolean("isadmin"));
                    } else {
                        return utilisateur;
                    }
                }
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
        return utilisateur;
    }

    public static List<Utilisateur> ListOfAll(){
        List<Utilisateur> listUtilisateur = new ArrayList<>();
        Statement stmt = null;
        try ( Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            String strSql = "SELECT * FROM Utilisateur";
            try  {
                stmt = connection.createStatement();
                ResultSet rs=stmt.executeQuery(strSql);
                while(rs.next()){
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setEmail(rs.getString("email"));
                    utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
                    utilisateur.setNom(rs.getString("nom"));
                    utilisateur.setDateInscription(rs.getTimestamp("date_inscription"));
                    utilisateur.setAdmin(rs.getBoolean("isadmin"));
                    listUtilisateur.add(utilisateur);
                }
                rs.close();
            } catch (SQLException s){
                s.printStackTrace();
            }
            connection.close();
            stmt.close();
        }catch (Exception exception) {
            throw new RuntimeException( exception );
        }
        return listUtilisateur;
    }

    public static List<Utilisateur> ListOfAllNoAdmin(){
        List<Utilisateur> listUtilisateur = new ArrayList<>();
        Statement stmt = null;
        try ( Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            String strSql = "SELECT * FROM Utilisateur WHERE isadmin = 0";
            try  {
                stmt = connection.createStatement();
                ResultSet rs=stmt.executeQuery(strSql);
                while(rs.next()){
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setEmail(rs.getString("email"));
                    utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
                    utilisateur.setNom(rs.getString("nom"));
                    utilisateur.setDateInscription(rs.getTimestamp("date_inscription"));
                    utilisateur.setAdmin(rs.getBoolean("isadmin"));
                    listUtilisateur.add(utilisateur);
                }
                rs.close();
            } catch (SQLException s){
                s.printStackTrace();
            }
            connection.close();
            stmt.close();
        }catch (Exception exception) {
            throw new RuntimeException( exception );
        }
        return listUtilisateur;
    }
}
