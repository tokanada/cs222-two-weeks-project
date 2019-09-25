package edu.bsu.cs222;

import java.util.*;
import java.util.stream.Collectors;

public class RevisionSorter {


    public List<Revision> sortMostRevisions(List<Revision> unsortedList) {

        List<String> usernameList = populateList(unsortedList);
        Map<String, Integer> unsortedMap = new HashMap<>();
        unsortedMap = countOccurrences(usernameList, unsortedMap);
        Map<String, Integer> sortedMap = sortMap(unsortedMap);
        List<Revision> sortedRevisionList = sortRevisionList(sortedMap, unsortedList);

        return sortedRevisionList;
    }

    private List<Revision> sortRevisionList(Map<String, Integer> sortedMap, List<Revision> unsortedList) {
        List<Revision> sortedList = new ArrayList<>();

        List<String> usernameList = getUserList(sortedMap);

        for(String username : usernameList) {
            for(Revision revision : unsortedList) {
                if(username.equals(revision.getUser())){
                    sortedList.add(revision);
                    unsortedList.remove(revision);
                    break;
                }
            }

        }

        return sortedList;
    }

    private List<String> getUserList(Map<String, Integer> userMap) {
        List<String> userList = new ArrayList<>();
        for(String username : userMap.keySet()){
            for(int i = 0; i < userMap.get(username); i++) {
                userList.add(username);
            }
        }
        return userList;
    }

    private HashMap sortMap(Map<String, Integer> unsortedMap) {
        List linkedList = new LinkedList(unsortedMap.entrySet());

        Collections.sort(linkedList, new Comparator() {
            public int compare(Object firstValue, Object secondValue) {
                return ((Comparable) ((Map.Entry) (secondValue)).getValue())
                        .compareTo(((Map.Entry) (firstValue)).getValue());
            }
        });

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator iterator = linkedList.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
            System.out.println(entry.getKey());
        }
        return sortedHashMap;
    }

    private List<String> populateList(List<Revision> revisionList) {
        List<String>stringList = new ArrayList<>();
        for(int i = 0; i < revisionList.size(); i++) {
            stringList.add(i, revisionList.get(i).getUser());
        }
        return stringList;
    }

    private Map<String, Integer> countOccurrences(List<String> stringList, Map<String, Integer> frequencyMap) {
        for(String username : stringList){
            Integer count = frequencyMap.get(username);
            if (count == null){
                count = 0;
            }

            frequencyMap.put(username, count + 1);
        }

        return frequencyMap;
    }
}
