package com.theironyard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    public static ArrayList<Post> parsePost(String filename) throws FileNotFoundException {
        ArrayList<Post> posts = new ArrayList<>();
        File f = new File(filename);
        Scanner filescanner = new Scanner(f);
        while (filescanner.hasNext()) {
            String line = filescanner.nextLine();
            String[] columns =line.split("\\|");
            Post post  = new Post(Integer.valueOf(columns[0]), columns[1], columns[2]);
            posts.add(post);

        }
        return posts;
    }

    public static void printPosts(ArrayList<Post> posts, int currentPost) {
        int postId = 0;
        for (Post post : posts) {
            if (post.replyId==currentPost) {
                System.out.printf("[%s] %s by %s\n",postId, post.text, post.author);

            }
            postId++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {


        //parse file
        ArrayList<Post> posts = parsePost("posts.txt");

        //start loop

        Scanner consoleScanner = new Scanner(System.in);
        int currentPost = -1;

        while (true) {

            printPosts(posts, currentPost);
            //ask for new id
            System.out.printf("Current post: %s\n", currentPost);
            System.out.println("Type the id you want to see replies to:");
            currentPost = Integer.valueOf(consoleScanner.nextLine());
        }
    }
}
