#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
#define MAX 200001
using namespace std;

int N,M;
vector<int> friends[MAX];
vector<int> rumors;
int answer[MAX];

int main(){
    cin >> N;

    for(int i=1;i<=N;i++){
        while(true){
            int num;
            cin >> num;

            if(num==0){
                break;
            }
            friends[i].push_back(num);
        }
        answer[i]=-1;
    }

    cin >> M;
    for(int i=0;i<M;i++){
        int a;
        cin >> a;
        rumors.push_back(a);
    }

    queue<int> q1;
    for(int i=0;i<M;i++){
        answer[rumors[i]]=0;

        for(int j=0;j<friends[rumors[i]].size();j++){
            q1.push(friends[rumors[i]][j]);
        }
    }

    queue<int> q2;
    int time=1;

    bool keep=false;
    while(true){
   
        keep=false;

        while(!q1.empty()){
            int num=q1.front();
            q1.pop();

            int cnt=0;
            for(int j=0;j<friends[num].size();j++){
                if(answer[friends[num][j]] >= 0){
                    cnt++;
                }
            }


            int half=friends[num].size()/2;
            if(friends[num].size()%2==1){
                half++;
            }
    
            if(cnt >= half){
                q2.push(num);
            }
        }

        queue<int> q3;
        // update
        while(!q2.empty()){
            int num=q2.front();
            q2.pop();
            answer[num]=time;
            q3.push(num);
        }

        while(!q3.empty()){
           int num=q3.front();
           q3.pop();

            for(int j=0;j<friends[num].size();j++){
                if(answer[friends[num][j]] < 0){
                    q1.push(friends[num][j]);
                    keep=true;

                }
            }
        }
        time++;

        if(!keep){
            break;
        }
    }

    for(int i=1;i<=N;i++){
        cout << answer[i] << " ";
    }
    cout << "\n";
}