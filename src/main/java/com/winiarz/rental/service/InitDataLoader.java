package com.winiarz.rental.service;

import com.winiarz.rental.model.User;
import com.winiarz.rental.model.UserRole;
import com.winiarz.rental.model.WarehouseItem;
import com.winiarz.rental.repository.ShoppingCartRepository;
import com.winiarz.rental.repository.UserOrderRepository;
import com.winiarz.rental.repository.UserRepository;
import com.winiarz.rental.repository.WarehouseItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class InitDataLoader {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseItemRepository warehouseItemRepository;

    @PostConstruct
    public void initialize() {
        List<WarehouseItem> warehouseItemList = new ArrayList<>();
        WarehouseItem warehouseItem = new WarehouseItem(null, "Narty zjazdowe 150", "https://ski24.pl/117434-large_default/narty-volkl-racetiger-rc-marker-vmotion-10-grip-walk.jpg", 30, 10, 300, "Narty zjazdowe z zapięciem Boots, idealnie nadaje się da osób w wieku 10-14lat.", true, Collections.emptyList());
        warehouseItemRepository.save(warehouseItem);
        warehouseItemList.add(warehouseItem);

        warehouseItem = new WarehouseItem(null, "Kije do nart pocket4", "http://wypozyczalniasprzetuturystycznego.pl/wp-content/uploads/2016/09/1295022905_5015-600x600.jpg", 5, 15, 50, "Kijki do nart zjazdowych", true, Collections.emptyList());
        warehouseItemRepository.save(warehouseItem);
        warehouseItemList.add(warehouseItem);

        warehouseItem = new WarehouseItem(null, "Kask ochronny", "https://contents.mediadecathlon.com/p1177822/k$599faea495ea2123db1bf8bd4617d523/sq/Kask+narciarski+H100.webp?f=1000x1000", 10, 15, 50, "Lekki ale pancerny kask Fuse Light to połączenie komfortu i efektywności", true, Collections.emptyList());
        warehouseItemRepository.save(warehouseItem);
        warehouseItemList.add(warehouseItem);

        warehouseItem = new WarehouseItem(null, "Rakiety śnieżne", "http://wypozyczalniasprzetuturystycznego.pl/wp-content/uploads/2018/01/tubbs_1718_flex-trk-mens-600x600.png", 40, 15, 100, "Doskonale zwrotna i ergonomiczna rakieta śnieżna Tubbs FLEX TRK zabierze Cię tam, dokąd chcesz się udać. FLEX TRK posiada łatwo regulowany podnośnik ActiveLift ™, aby umożliwić naturalny ruch w zmiennym terenie, a wiązanie QuickPu", true, Collections.emptyList());
        warehouseItemRepository.save(warehouseItem);
        warehouseItemList.add(warehouseItem);

        warehouseItem = new WarehouseItem(null, "Detektor lawinowy", "http://wypozyczalniasprzetuturystycznego.pl/wp-content/uploads/2016/09/arva-evo.jpg", 30, 10, 400, "ARVA EVOLUTION 4 to detektor lawinowy, który jest ostatnim i najlepszym z całej serii EVO od ARVY", true, Collections.emptyList());
        warehouseItemRepository.save(warehouseItem);
        warehouseItemList.add(warehouseItem);

        User user = new User(null, "admin", "admin", "123-123-123", "a@a", "aaa", UserRole.ADMIN, true, Collections.emptyList());
        userRepository.save(user);




    }
}
