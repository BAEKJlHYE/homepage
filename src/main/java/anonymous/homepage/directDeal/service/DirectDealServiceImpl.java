package anonymous.homepage.directDeal.service;

import anonymous.homepage.directDeal.dao.DirectDealMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectDealServiceImpl implements DirectDealService {
    private final DirectDealMapper directDealMapper;
}
