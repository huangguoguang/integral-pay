package com.acl.pay.service;

import com.acl.pay.entity.CzOrder;
import com.acl.pay.entity.TxOrder;
import com.acl.pay.entity.UserProfitLoss;
import com.acl.pay.utils.Pagination;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-26 10:10
 */
public interface UserPaymentService {
    /**
     * 新增用户充值订单
     **/
    boolean insertCzOrder(CzOrder czOrder);

    /**
     * 处理用户充值订单
     **/
    boolean processUserRechargeOrder(CzOrder czOrder);

    /**
     * 处理用户提现订单
     **/
    boolean processUserWithdrawOrder(TxOrder txOrder);

    /**
     * 新增用户提现订单
     **/
    boolean insertTxOrder(TxOrder TxOrder);

    /**
     * 查询用户流水
     *
     * */
    Pagination<UserProfitLoss> getUserStatement(String user_id, Date stime, Date etime, int page, int row);


    /**
     * 用户线下转让订单，一方当作兑换积分，一方当作获取积分
     * @param txOrder
     * @return
     */
    boolean processUserToUserOrder(TxOrder txOrder, CzOrder czOrder);

}
