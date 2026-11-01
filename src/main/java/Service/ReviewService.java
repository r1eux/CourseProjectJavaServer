package Service;

import Dao.ReviewDAO;
import Entities.Review;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class ReviewService implements Service<Review> {
    DAO daoService = new ReviewDAO();

    @Override
    public Review findEntity(int id) {
        return (Review) daoService.findById(id);
    }

    @Override
    public void saveEntity(Review entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Review entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Review entity) {
        daoService.update(entity);
    }

    @Override
    public List<Review> findAllEntities() {
        return daoService.findAll();
    }
    public List<Review>findAllReviews(int prodId) {
        ReviewDAO reviewDAO = new ReviewDAO();
        return reviewDAO.findByProdId(prodId);
    }
}
