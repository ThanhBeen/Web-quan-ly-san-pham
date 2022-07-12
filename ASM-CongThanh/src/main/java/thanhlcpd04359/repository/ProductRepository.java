package thanhlcpd04359.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thanhlcpd04359.domain.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
