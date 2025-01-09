#include <iostream>
#include<algorithm>
#include<vector>
#include<map>
#include <unordered_map>
#define MAX 200001
using namespace std;

int N,K;
vector<int> a;
vector<int> b;
vector<int> c;
unordered_map<int,int> m;

int main() {
    cin >> N >> K;
    for(int i=0;i<N;i++){
        int num;
        cin >> num;
        a.push_back(num);
    }
    for(int i=0;i<N;i++){
        int num;
        cin >> num;
        b.push_back(num);
    }
    for(int i=0;i<K;i++){
        int num;
        cin >> num;
        c.push_back(num);
    }

    // 일단 선물상자를 정렬
    sort(b.begin(),b.end());
    sort(a.begin(),a.end());

    for(int i=0;i<K;i++){
        int person=c[i];
        m[person]++; // 상품 타감
    }


    int my=0;

    for(int i=N-1;i>=0;i--){
        int box=b[i];
        if(m[box]>=1){
            m[box]--;
        }
        else{
            my=box;
            break;
        }
    }

    int answer=0;

    for(int i=N-1;i>=0;i--){
        // if(a[i] >= my){
        //     answer=a[i];
        // }
        // else{
        //     break;
        // }
        if(my >= a[i]){
            answer=a[i];
            break;
        }
    }

    cout << answer << "\n";
    


/*
7 3
11 3 5 13 9 1 7
1 9 6 9 13 13 5
13 9 13

선물 : 1 3 5 7 9 11 13
상자 : 1 5 6 9 9 13 13
가져간 상자 : 9, 13 ,13

가장 큰 상자는 9 가능. 
*/

    return 0;
}

