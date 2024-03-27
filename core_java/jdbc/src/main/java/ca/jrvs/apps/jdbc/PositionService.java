package ca.jrvs.apps.jdbc;

import java.util.Optional;

public class PositionService {

    private PositionDao dao;

    public PositionService(PositionDao dao){
        this.dao = dao;
    }

    /**
     * Processes a buy order and updates the database accordingly
     * @param ticker
     * @param numberOfShares
     * @param price
     * @return The position in our database after processing the buy
     */
    public Position buy(String ticker, int numberOfShares, double price) {
        Position position = new Position();
        position.setTicker(ticker);
        position.setNumOfShares(numberOfShares);
        position.setValuePaid(price);


        Optional<Position> ownPosition =  dao.findById(ticker);
        if(ownPosition.isPresent()){
            position.setValuePaid(ownPosition.get().getValuePaid() + price);
            position.setNumOfShares(ownPosition.get().getNumOfShares() + numberOfShares);
        }
        dao.save(position);
        return position;
    }

    /**
     * Sells all shares of the given ticker symbol
     * @param ticker
     */
    public void sell(String ticker) {
        dao.deleteById(ticker);
    }

}
