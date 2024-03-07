package DAO;

import model.Investor;

import java.util.List;
public interface InvestorDAO {
    void addInvestor(Investor investor);
    void deleteInvestor(Investor investor);
    List<Investor> getAllInvestors();
}
