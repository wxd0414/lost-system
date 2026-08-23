CREATE TABLE `found_item_register` (
                                       `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                       `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                       `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户姓名',
                                       `phone` varchar(20) DEFAULT NULL COMMENT '用户手机号',
                                       `lost_item_name` varchar(100) DEFAULT NULL COMMENT '捡到失物名称',
                                       `lost_item_description` varchar(255) DEFAULT NULL COMMENT '捡到失物描述',
                                       PRIMARY KEY (`id`),
                                       KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='捡到失物信息登记表';



CREATE TABLE `lost_item_register` (
                                      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                      `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户姓名',
                                      `phone` varchar(20) DEFAULT NULL COMMENT '用户手机号',
                                      `lost_item_name` varchar(100) DEFAULT NULL COMMENT '失物名称',
                                      `lost_item_description` varchar(255) DEFAULT NULL COMMENT '失物描述',
                                      PRIMARY KEY (`id`),
                                      KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='失物信息登记表';



CREATE TABLE `chat_history` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                                `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                `session_id` varchar(255) DEFAULT NULL COMMENT '会话id',
                                `role` varchar(50) DEFAULT NULL COMMENT '角色',
                                `content` longtext COMMENT '内容',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=379 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聊天历史';




CREATE TABLE `chat_msg` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
                            `uid` int DEFAULT NULL COMMENT '用户id',
                            `message` varchar(2048) DEFAULT NULL COMMENT '对话消息内容',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聊天历史记录表';