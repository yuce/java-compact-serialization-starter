/*
 * Copyright (c) 2008-2023, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example;

import clc.ConfigLoader;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class Main {

    public static void main(String[] args) throws Exception {
        ClientConfig config = ConfigLoader.loadConfig("c21");
        config.getSerializationConfig().getCompactSerializationConfig().setSerializers(
                new org.example.School.Serializer(),
                new org.example.Classroom.Serializer(),
                new org.example.Student.Serializer()
        );
        HazelcastInstance client = HazelcastClient.newHazelcastClient(config);
        System.out.println("Connection Successful!");

        IMap<String, Student> students = client.getMap("students");
        students.set("joe", new Student(1, "Joe Dalton"));
        students.set("william", new Student(2, "William Dalton"));
        students.set("jack", new Student(3, "Jack Dalton"));
        students.set("Averell", new Student(4, "Averell Dalton"));
        System.out.println("Added the students!");

        client.shutdown();
    }
}
