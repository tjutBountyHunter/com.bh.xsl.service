package service;

import util.XslResult;

public interface HunterShop {
    XslResult hunterShop(Integer rows, Integer userId, Integer hunterId);

    XslResult hunterShopCount(Integer useId, Integer rows);

    XslResult hunterOne(Integer hunterid);
}
