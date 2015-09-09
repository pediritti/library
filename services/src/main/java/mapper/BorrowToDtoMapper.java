package mapper;

import domain.Borrowed;
import dtos.BorrowingDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BorrowToDtoMapper implements ToDtoMapper<Borrowed, BorrowingDTO> {

    @Override
    public BorrowingDTO map(Borrowed entity) {
        return null;
    }

    @Override
    public List<BorrowingDTO> map(List<Borrowed> entities) {
        return null;
    }
}
