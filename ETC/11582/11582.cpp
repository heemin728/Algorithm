#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main(){
    int N;
    cin >> N;

    vector<int> v;

    for(int i=0;i<N;i++){
        int a;
        cin >> a;

        v.push_back(a);
    }

    int find;
    cin >> find;
    
    int cnt=N/2;

    while(cnt >= 1){
        // 
        int num=N/cnt;

        for(int i=0;i<N-1;i+=num){
            sort(v.begin()+i,v.end()+i+num);
        }

        if(cnt==find){
            break;
        }
        cnt/=2;
    }

    for(int i=0;i<N;i++){
        cout << v[i] << " ";
    }

}