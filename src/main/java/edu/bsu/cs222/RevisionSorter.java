package edu.bsu.cs222;

import java.util.*;

class RevisionSorter {


    List<Revision> sortByMostRevisions(List<Revision> unsortedList) {
        try {
            List<String> usernameList = populateList(unsortedList);
            Map<String, Integer> unsortedMap = new HashMap<>();
            countOccurrences(usernameList, unsortedMap);
            HashMap sortedMap = sortMap(unsortedMap);
            return sortRevisionList(sortedMap, unsortedList);
        } catch (NullPointerException e) {
            return null;
        }
    }

    List<Revision> groupByIP(List<Revision> ungroupedList) {
        List<Revision> anonList = new ArrayList<>();
        List<Revision> sortedList = sortByMostRevisions(ungroupedList);
        int sortedListSize = sortedList.size();
        for(int i = 0; i < sortedListSize; i++) {
            separateAnonymous(sortedList, anonList);
        }
        sortedList.addAll(anonList);
        return sortedList;
    }

    private List<Revision> sortRevisionList(Map<String, Integer> sortedMap, List<Revision> unsortedList) {
        List<Revision> sortedList = new ArrayList<>();
        List<String> usernameList = getUserList(sortedMap);
        for(String username : usernameList) {
            compareRevisionLists(username, unsortedList, sortedList);
        }
        return sortedList;
    }

    private void separateAnonymous(List<Revision> sortedList, List<Revision> anonList) {
        for (Revision revision : sortedList) {
            if (revision.isAnonymous()) {
                anonList.add(revision);
                sortedList.remove(revision);
                break;
            }
        }
    }

    private void compareRevisionLists(String username, List<Revision> unsortedList, List<Revision> sortedList) {
        for(Revision revision : unsortedList) {
            if(username.equals(revision.getUser())){
                sortedList.add(revision);
                unsortedList.remove(revision);
                break;
            }
        }
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
        List<Object> linkedList = new LinkedList<>(unsortedMap.entrySet());
        Collections.sort(linkedList, new Comparator() {
            public int compare(Object firstValue, Object secondValue) {
                return ((Comparable) ((Map.Entry) (secondValue)).getValue())
                        .compareTo(((Map.Entry) (firstValue)).getValue());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Object o : linkedList) {
            Map.Entry entry = (Map.Entry) o;
            sortedHashMap.put(entry.getKey(), entry.getValue());
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

    private void countOccurrences(List<String> stringList, Map<String, Integer> frequencyMap) {
        for(String username : stringList){
            Integer count = frequencyMap.get(username);
            if (count == null){
                count = 0;
            }
            frequencyMap.put(username, count + 1);
        }
    }
}
