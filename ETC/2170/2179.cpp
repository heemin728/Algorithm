#include<iostream>
#include<algorithm>
#include<vector>
#define MAX 1000001
using namespace std;

int N;
pair<int,int> v[MAX];

int main(){
    ios_base :: sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    for(int i=0;i<N;i++){
        int x, y;
        cin >> x >> y;
        v[i]={x,y};
    }

    sort(v,v+N);

    int start=v[0].first;
    int end=v[0].second;

    int answer=0;
    answer+=v[0].second-v[0].first;

    for(int i=1;i<N;i++){
        int x=v[i].first;
        int y=v[i].second;

        if(x>=start && x<=end){
            if(y <= end){
                continue;
            }

            answer+=(y-end);
            end=y;
        }
        else{
            start=x;
            end=y;
            answer+=(end-start);
        }
    }    
    cout << answer << "\n";

}