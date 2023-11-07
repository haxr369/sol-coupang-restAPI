//package com.example.solcoupang.user.model;
//
//import jakarta.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public abstract class KafkaListenerAbstract<T> {
//    // Bean으로 떴을 때 로깅
//    // 공통됐지만 매번 동일한 로직을 수행한다면 굳이 매번 구현할 필요없다. 그래서 추상 클래스를 사용해서 미리 구현해주자.
//    @PostConstruct // 생성자로 객체 생성했을 때 해당 메서드를 실행한다.
//    public void instantiated() {
//        // 어떻게 "UserKafkaBody" 대신에 Kafka에 접근하는 객체의 이름을 넣을 수 있을까?
//        log.info("UserKafkaBody bean is creadted {}", getType());
//    }
//    abstract KafkaType getType();
//
//    public abstract void consume(UserKafkaBody consumed);
//    /**
//     * ㅋㅏ프카를 새롭게 발생하면
//     * 카프카 컨슈머를 새롭게 만들어서 Bean이 떴을 때 로그가 찍혀야하는 것
//     */
//}
//
//@Slf4j
//class PaymentKafkaListenserAbstract extends KafkaListenerAbstract<UserKafkaBody>{
//    @Override
//    KafkaType getType() {
//        return KafkaType.PAYMENT_KAFKA;
//    }
//
//    // Bean으로 떴을 때 로깅
//    // 공통됐지만 매번 동일한 로직을 수행한다면 굳이 매번 구현할 필요없다. 그래서 추상 클래스를 사용해서 미리 구현해주자.
//
//    @Override
//    public void consume(UserKafkaBody consumed) {
//        // 중앙화하는 로직 구현
//        log.info("cunsumed {}",consumed);
//    }
//}