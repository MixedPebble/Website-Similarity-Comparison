# Website-Similarity-Comparison
This application makes a similiarity comparison between different websites using unique words and their frequencies. There needs to be a source of websites to be compared against and a website URL that the user gives. This application works by grabbing the URL from the user and giving it to a URLReader object. The words from the website are then hashed for their frequencies. The application then makes a comparrision between the given website and the list of website(s) to compare against by calculating the percentage of words that are similiar and their frequency is used as a weight. This process is repeated for each website and the similarity percentage will be displayed.

EX: Comparing two links on Java Concurrency may have a similarity percentage of 80% and comparing a Java Concurrency link against an article on mashed potatos may yield a similarity percentage of just 10%. Comparing a link against itself will yield 100% similarity.

