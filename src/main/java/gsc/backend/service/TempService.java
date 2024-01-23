package gsc.backend.service;

import gsc.backend.repository.TempRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TempService {
    private final TempRepository tempRepository;
}
